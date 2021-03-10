package BusApp;

import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken;
import com.thalesgroup.rtti._2017_10_01.ldb.GetBoardRequestParams;
import com.thalesgroup.rtti._2017_10_01.ldb.LDBServiceSoap;
import com.thalesgroup.rtti._2017_10_01.ldb.Ldb;
import com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType;
import com.thalesgroup.rtti._2017_10_01.ldb.types.ServiceItem;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Open Live Departure Boards Web Service (OpenLDBWS) API Demonstrator
 * Copyright (C)2018 OpenTrainTimes Ltd.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class SoapClient {

    private static final Logger logger = LoggerFactory.getLogger(SoapClient.class);

    private static final String LDB_TOKEN = "0c5860e6-9535-431c-a31f-628676af5765";
    private static final boolean DEBUG = false;

    public static List<ServiceItem> getDepartureBoards(String crs) throws ConfigurationException {
        try {
            AccessToken accessToken = new AccessToken();
            accessToken.setTokenValue(LDB_TOKEN);
            Ldb soap = new Ldb();
            LDBServiceSoap soapService = soap.getLDBServiceSoap12();

            if (DEBUG) {
                Client client = ClientProxy.getClient(soapService);
                client.getInInterceptors().add(new LoggingInInterceptor());
                client.getOutInterceptors().add(new LoggingOutInterceptor());
            }

            return getDepartures(accessToken, soapService, crs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<ServiceItem> getDepartures(AccessToken accessToken, LDBServiceSoap soapService, String crs) {
        GetBoardRequestParams params = new GetBoardRequestParams();
        params.setCrs(crs);


        StationBoardResponseType departureBoard = soapService.getDepartureBoard(params, accessToken);
        return departureBoard.getGetStationBoardResult().getTrainServices().getService();
    }

    public static ArrayList<Train> getNationalRailTrains(String crs, String destination) {
        ArrayList<Train> trainList = null;
        try {
            List<ServiceItem> serviceItemList = SoapClient.getDepartureBoards(crs);
            assert serviceItemList != null;
            trainList = ServiceItemParser.parseServiceItem(serviceItemList, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainList;
    }
}