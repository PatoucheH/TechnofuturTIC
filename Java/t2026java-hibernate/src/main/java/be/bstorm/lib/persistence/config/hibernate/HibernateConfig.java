package be.bstorm.lib.persistence.config.hibernate;

import lombok.Data;

@Data
public class HibernateConfig {
    private String dialect;
    private boolean showSql = true;
    private boolean formatSql = true;

    private HibernateHbm2ddlConfig hbm2ddl;
}
