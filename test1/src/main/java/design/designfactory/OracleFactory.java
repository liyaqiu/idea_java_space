package design.designfactory;


import design.designbulider.Config;
import design.designbulider.DBLeader;
import design.designbulider.MySqlBuilder;

public class OracleFactory extends ConfigFactory {
    @Override
    public Config getWindowConfig() {
        DBLeader dbLeader = new DBLeader(new MySqlBuilder("mysqluser","123456"));
        return dbLeader.genericMysqlConfig();
    }
    @Override
    public Config getLinuxConfig() {
        DBLeader dbLeader = new DBLeader(new MySqlBuilder("mysqluser","123456"));
        return dbLeader.genericMysqlConfig();
    }
}
