package com.roy.landcomparator.datasource;

public interface IQuery {

    public static final String SELECT_ALL_SQL = "SELECT t.cidnum, t.nkmstart, t.nkmend, t.nkrplanyear, t.cpipename"
            + "  FROM rk_calc_section t"
            + "  WHERE t.bisrealsection = 1"
            + "  AND t.nstate = 1 ";

    public static final String UPDATE_KM_SQL = "UPDATE rk_calc_section t"
            + " SET t.NKMSTART = ?,"
            + " t.NKMEND = ? "
            + " WHERE t.CIDNUM = ?";

    public static final String UPDATE_YEAR_SQL = "UPDATE rk_calc_section t"
            + " SET t.NKRPLANYEAR = ?"
            + " WHERE t.CIDNUM = ?";

    public static final String UPDATE_CPIPENAME_SQL = "UPDATE rk_calc_section t"
            + " SET t.CPIPENAME = ?"
            + " WHERE t.CIDNUM = ?";
}
