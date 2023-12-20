package design.designbulider;

public class Test {
    public static void main(String[] args) {
        DBLeader dbLeader = new DBLeader(new MySqlBuilder("mysqluser","123456"));
        System.out.println(dbLeader.genericMysqlConfig());
        dbLeader.setDbBuilder(new OracleBuilder("oracleuser","123456"));
        System.out.println(dbLeader.genericOracleConfig());

        new ConfigBuilder.Builder("oracle").setSql("").setParam("").build();

        new ConfigBuilder().getBuilder("oracle").build();

    }
}
