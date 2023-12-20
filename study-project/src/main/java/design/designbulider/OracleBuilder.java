package design.designbulider;

public class OracleBuilder extends DBBuilder{
    ConfigEntity configEntity = new ConfigEntity();


    public OracleBuilder(String uName, String passwd) {
       configEntity.setUserName(uName);
       configEntity.setPasswd(passwd);
    }

    @Override
    public void setSql() {
        configEntity.setSql("oracle:select * from table");

    }

    @Override
    public void setParam1() {
        configEntity.setParam1("a1");

    }

    @Override
    public void setParam2() {
        configEntity.setParam2("a2");

    }

    @Override
    public void setDBType() {
        configEntity.setDBType("oracle");

    }

    @Override
    public Config getConfig() {
        return configEntity;
    }
}
