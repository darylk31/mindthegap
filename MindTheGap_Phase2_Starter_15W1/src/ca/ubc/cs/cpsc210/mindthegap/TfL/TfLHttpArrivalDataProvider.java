package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String stopPointId = stn.getID();
        String id = null;
        for (Line l : stn.getLines()) {
            if (id == null) {
                id = l.getId();
            }
            else {
                id = id+','+l.getId();
            }
        }
        String url1 = "https://api.tfl.gov.uk/Line/";
        String url2 = "/Arrivals?stopPointId=";
        String url3 = "&app_id=&app_key=";
        String request = (url1 + id + url2 + stopPointId + url3);
        URL url = new URL(request);
        return url;
    }
}
