package com.roy.landcomparator.compare;

import com.roy.landcomparator.beans.Land;
import com.roy.landcomparator.datasource.DataBaseManager;
import static com.roy.landcomparator.datasource.IQuery.UPDATE_KM_SQL;
import com.roy.landcomparator.gui.Comparator;
import com.roy.landcomparator.xlsx.ExcelFileManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandComparator {

    DataBaseManager dataBaseManager = null;

    public LandComparator(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public Map<String, List<Double>> compareLands(Map<Double, Land> listFromFile, Map<Double, Land> listFromDB) throws IOException {

        Map<String, List<Double>> list = new HashMap<>();

        List<Double> tempNA = new ArrayList<>();
        tempNA.add(0.1);
        List<Double> tempU = new ArrayList<>();
        tempU.add(0.2);

        for (Double key : listFromFile.keySet()) {

            Land landFromFile = listFromFile.get(key);

            Land landFromDB = null;

            if (listFromDB.containsKey(key)) {
                landFromDB = listFromDB.get(key);
            } else {
                tempNA.add(key);
                System.out.println("Land with key:" + key + "is not avalible in data base!");
                ExcelFileManager.setCellColoreIfNotAvailable(key);
            }
            try {

                if (landFromDB == null) {
                    continue;
                }

                if (landFromFile.getNKMSTART() != landFromDB.getNKMSTART() || landFromFile.getNKMEND() != landFromDB.getNKMEND()) {

                    if (dataBaseManager.KmUpdate(key, landFromFile) > 0) {
                        tempU.add(key);
                    }

                } else if (landFromFile.getNKRPLANYEAR() != landFromDB.getNKRPLANYEAR()) {

                    if (dataBaseManager.YearUpdate(key, landFromFile) > 0) {
                        tempU.add(key);
                    }

                } else if (!landFromFile.getCPIPENAME().trim().equals(landFromDB.getCPIPENAME().trim())) {
                    if (dataBaseManager.NameUpdate(key, landFromFile) > 0) {
                        tempU.add(key);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list.put("notAvalible", tempNA);
        list.put("updated", tempU);
        return list;
    }
}
