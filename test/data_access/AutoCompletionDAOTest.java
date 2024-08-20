package data_access;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompletionDAOTest {

    @Test
    void testAutoCompletionDAO() {
        try {
            AutoCompletionDAO autoCompletionDAO = new AutoCompletionDAO();
            assertInstanceOf(com.google.maps.model.AutocompletePrediction[].class, autoCompletionDAO.getListOfPredictions("UofT"));
        }
        catch (Exception e) {
            fail();
        }

    }

}
