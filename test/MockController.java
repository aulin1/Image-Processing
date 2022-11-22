import java.util.Objects;

import controller.IPFeature;
import view.ImageProcessingView;

/**
 * This class represents a mock controller for testing.
 */
public class MockController implements IPFeature {
  private final StringBuilder receivedInput;

  /**
   * Default constructor for a Mock Controller.
   *
   * @param receivedInput the input received from the view
   * @throws IllegalArgumentException if the stringbuilder is null
   */
  public MockController(StringBuilder receivedInput, ImageProcessingView view) throws IllegalArgumentException {
    this.receivedInput = Objects.requireNonNull(receivedInput);
    view.registerFeature(this);
  }

  @Override
  public void processInput(String input) {
    this.receivedInput.append(input);
  }
}
