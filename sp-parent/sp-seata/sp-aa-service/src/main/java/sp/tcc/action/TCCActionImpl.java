package sp.tcc.action;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sp.tcc.dao.AccountMapper;
import sp.tcc.dao.AccountTccMapper;
import sp.tcc.entity.AccountTccEntity;

/**
 * @author lyq
 * @date 2022/1/26 10:33
 */
@Service
@Slf4j
public class TCCActionImpl implements TCCAction {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountTccMapper accountTccMapper;


    @Transactional
    @Override
    public void tryTest(String uid, int money) {
        String xid = RootContext.getXID();
        log.info("tryTest....xid:{}",xid);
        //悬挂处理
        AccountTccEntity oldAccount = accountTccMapper.selectById(xid);
        if(oldAccount!=null){
            return;
        }

        accountMapper.deduct(uid,money);

        AccountTccEntity accountTccEntity = new AccountTccEntity();
        accountTccEntity.setXid(xid);
        accountTccEntity.setState(AccountTccEntity.State.TRY);
        accountTccEntity.setUid(uid);
        accountTccEntity.setDongjieMoney(money);
        accountTccMapper.insert(accountTccEntity);
        return;
    }

    /**
     * 返回false  会被事务协调器一直通知调用
     * */
    @Override
    @Transactional
    public boolean confirmTest(BusinessActionContext ctx) {

        String xid = ctx.getXid();
        log.info("confirmTest....xid:{}------this:{}",xid,this);
        //幂等处理
        AccountTccEntity accountTccEntity = accountTccMapper.selectById(xid);
        if(accountTccEntity == null){
            return true;
        }
        int i = accountTccMapper.deleteById(xid);
        return i==1;
    }

    /**
     * 返回false  会被事务协调器一直通知调用
     * */
    @Override
    @Transactional
    public boolean cancelTest(BusinessActionContext ctx) {
        String xid = ctx.getXid();
        log.info("cancelTest....{}",xid);
        String uid = ctx.getActionContext("uid").toString();
        AccountTccEntity accountTccEntity = accountTccMapper.selectById(xid);
        //空回滚
        if(accountTccEntity == null){
            accountTccEntity = new AccountTccEntity();
            accountTccEntity.setXid(xid);
            accountTccEntity.setState(AccountTccEntity.State.CANCEL);
            accountTccEntity.setUid(uid);
            accountTccEntity.setDongjieMoney(0);
            return true;
        }
        //幂等判断
        if(AccountTccEntity.State.CANCEL == accountTccEntity.getState()){
            return true;
        }

        accountTccEntity.setState(AccountTccEntity.State.CANCEL);
        accountTccMapper.updateById(accountTccEntity);

        int i = accountMapper.refund(accountTccEntity.getUid(), accountTccEntity.getDongjieMoney());
        return i == 1;

    }
}
