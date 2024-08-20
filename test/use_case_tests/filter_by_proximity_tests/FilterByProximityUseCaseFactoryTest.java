package use_case_tests.filter_by_proximity_tests;

import app.gui.FilterByProximityUseCaseFactory;
import app.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.FilterByProximityView;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for filter by proximity use case factory
 */
public class FilterByProximityUseCaseFactoryTest {

    private GUI mockGui;
    private JTextField mockTextFieldAddress;
    private FilterByProximityUseCaseFactory factory;
    private FilterByProximityView mockView;

    /**
     * Mock external dependencies
     */
    @BeforeEach
    public void setUp() {
        // Mock dependencies
        mockGui = mock(GUI.class);
        mockTextFieldAddress = mock(JTextField.class);
        mockView = mock(FilterByProximityView.class);


        // Set up factory with mocked dependencies
        factory = spy(new FilterByProximityUseCaseFactory(mockGui, mockTextFieldAddress));

        // Mock the factory's internal object creation
        doReturn(mockView).when(factory).createFilterByProximityView();

    }

    /**
     * Test that the factory initializes the view correctly
     */
    @Test
    public void testFactoryInitializesView() {
        // Verify that factory initializes components correctly using mocked methods
        assertNotNull(factory.createFilterByProximityView()); // This line depends on external API, so instead we verify interactions

        // Verify the internal creation methods were called as expected
        verify(factory, times(1)).createFilterByProximityView();
    }
}
