package design.designfactory;

import design.designbulider.Config;
import design.designbulider.DBLeader;
import design.designbulider.OracleBuilder;

public class MysqlFactory extends ConfigFactory {

    @Override
    public Config getWindowConfig() {
        DBLeader dbLeader = new DBLeader(new OracleBuilder("mysqluser", "123456"));
        return dbLeader.genericMysqlConfig();
    }

    @Override
    public Config getLinuxConfig() {
        DBLeader dbLeader = new DBLeader(new OracleBuilder("mysqluser", "123456"));
        return dbLeader.genericMysqlConfig();
    }
}
