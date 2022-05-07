package sp.tcc.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 声明TCC接口
 * @author lyq
 * @date 2022/1/26 10:28
 */

@LocalTCC
public interface TCCAction {
    @TwoPhaseBusinessAction(name = "tryTest",commitMethod = "confirmTest",rollbackMethod = "cancelTest")
    void tryTest(@BusinessActionContextParameter(paramName = "uid") String uid,
                @BusinessActionContextParameter(paramName = "money") int money);
    boolean confirmTest(BusinessActionContext ctx);
    boolean cancelTest(BusinessActionContext ctx);
}
