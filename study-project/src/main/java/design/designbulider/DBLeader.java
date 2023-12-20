package design.designbulider;

public class DBLeader {
    private DBBuilder dbBuilder;

    public DBLeader(DBBuilder dbBuilder) {
        this.dbBuilder = dbBuilder;
    }

    public void setDbBuilder(DBBuilder dbBuilder) {
        this.dbBuilder = dbBuilder;
    }

    public Config genericMysqlConfig(){
        dbBuilder.setSql();
        dbBuilder.setParam1();
        dbBuilder.setParam2();
        dbBuilder.setDBType();
        return dbBuilder.getConfig();
    }

    public Config genericOracleConfig(){
        dbBuilder.setSql();
        dbBuilder.setParam1();
        dbBuilder.setParam2();
        dbBuilder.setDBType();
        return dbBuilder.getConfig();
    }
}
