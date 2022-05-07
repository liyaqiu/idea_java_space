package design.designbulider;

public class MySqlBuilder extends DBBuilder{
    ConfigEntity configEntity = new ConfigEntity();

    public MySqlBuilder(String uName, String passwd) {
        configEntity.setUserName(uName);
        configEntity.setPasswd(passwd);
    }

    @Override
    public void setSql() {
        configEntity.setSql("mysql:select * from table");
        
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
        configEntity.setDBType("mysql");
        
    }

    @Override
    public Config getConfig() {
        return configEntity;
    }
}
