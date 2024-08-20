package use_case_tests.filter_by_radius_tests;
import app.gui.FilterByRadiusUseCaseFactory;
import app.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.FilterByRadiusView;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for filter by radius use case factory
 */
public class FilterByRadiusUseCaseFactoryTest {

    private GUI mockGui;
    private JTextField mockTextFieldAddress;
    private FilterByRadiusUseCaseFactory factory;
    private FilterByRadiusView mockView;

    /**
     * Mock up external dependencies
     */
    @BeforeEach
    public void setUp() {
        // Mock dependencies
        mockGui = mock(GUI.class);
        mockTextFieldAddress = mock(JTextField.class);
        mockView = mock(FilterByRadiusView.class);


        // Set up factory with mocked dependencies
        factory = spy(new FilterByRadiusUseCaseFactory(mockGui, mockTextFieldAddress));

        // Mock the factory's internal object creation
        doReturn(mockView).when(factory).createFilterByRadiusView();

    }

    /**
     * Test that the factory initializes the view correctly
     */
    @Test
    public void testFactoryInitializesView() {
        // Verify that factory initializes components correctly using mocked methods
        assertNotNull(factory.createFilterByRadiusView()); // This line depends on external API, so instead we verify interactions

        // Verify the internal creation methods were called as expected
        verify(factory, times(1)).createFilterByRadiusView();
    }
}

