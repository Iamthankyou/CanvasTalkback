# CanvasTalkBack

CanvasTalkBack is an accessibility library that integrates TalkBack support for interactive areas on a Canvas. It allows visually impaired users to receive voice feedback and interact with touchable regions on custom Canvas views. This library simplifies adding accessibility features to graphical applications, ensuring inclusivity and better user experience.

## Features

- Adds TalkBack support to interactive areas on a Canvas.
- Provides content descriptions for accessible regions.
- Manages touch events for enhanced accessibility.
- Dynamically updates accessibility focus when the Canvas elements change.
- Compatible with custom `Canvas` views for easy integration.

## Installation

To install CanvasTalkBack, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/Iamthankyou/CanvasTalkBack.git
   ```

2. Add the library to your project (for Android projects, include the files in your `libs` directory or via a package manager).

3. In your `CustomCanvasView` class, extend `ExploreByTouchHelper` and implement the `CanvasTalkBackView` interface to set up interactive areas.

## Usage

1. **Set up the `CanvasTalkBack` in your custom view**:
   
   Implement the `CanvasTalkBackView` interface in your `CustomCanvasView` to define interactive areas and content descriptions.

2. **Update interactive areas**:
   
   Use the `updateInteractiveAreaPosition()` method to dynamically update the position or size of interactive regions.

3. **Integrate accessibility support**:
   
   Add `ViewCompat.setAccessibilityDelegate(this, mTouchHelper)` to connect the `ExploreByTouchHelper` to your view for handling touch and hover events.

Example of setting up in `CustomCanvasView`:

```java
public class CustomCanvasView extends View implements CanvasTalkBackView {
    private List<InteractiveArea> mInteractiveAreas;

    public CustomCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInteractiveAreas = new ArrayList<>();
        mInteractiveAreas.add(new InteractiveArea(new Rect(100, 100, 500, 500), "This is a clickable area"));
        mTouchHelper = new DoodleAIDragTouchHelper(this, this);
        ViewCompat.setAccessibilityDelegate(this, mTouchHelper);
    }
    // Implement other methods...
}
```

## Contributing

We welcome contributions! If you have suggestions, bug fixes, or want to add new features, feel free to submit a pull request.

1. Fork the repository.
2. Create a new branch for your feature.
3. Commit your changes.
4. Push to your fork.
5. Create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README gives an overview of the project, installation instructions, usage examples, and contribution guidelines. Feel free to customize it based on the exact needs of your project!
