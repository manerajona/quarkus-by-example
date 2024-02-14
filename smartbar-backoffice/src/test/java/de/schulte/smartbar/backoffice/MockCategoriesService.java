package de.schulte.smartbar.backoffice;

import de.schulte.smartbar.backoffice.api.model.Category;
import io.quarkus.test.Mock;

@Mock
public class MockCategoriesService extends CategoriesService {

    @Override
    public Category get() {
        return new Category().name("Mock");
    }
}
