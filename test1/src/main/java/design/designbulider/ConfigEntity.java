package design.designbulider;

public class ConfigEntity implements Config{
    private String userName;
    private String passwd;
    private String sql;
    private String param1;
    private String param2;
    private String DBType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getDBType() {
        return DBType;
    }

    public void setDBType(String DBType) {
        this.DBType = DBType;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "sql='" + sql + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", DBType='" + DBType + '\'' +
                '}';
    }
}
