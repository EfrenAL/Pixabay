package com.efren.pixabay.view.details;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.repositories.ImageRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by efren.lamolda on 14.08.18.
 */
public class DetailsViewModelTest {

    public static final String TEST_URL_0 = "testURL0";

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public ImageRepository imageRepository;

    private DetailsViewModel detailsViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        detailsViewModel = new DetailsViewModel(imageRepository);
    }

    @Test
    public void getImage() throws Exception {

        when(imageRepository.getImage(1)).thenReturn(mockImage());

        LiveData<Image> image = detailsViewModel.getImage(1);
        assertEquals(image.getValue().getImageURL(), TEST_URL_0);
        assertEquals(image.getValue().getId().toString(), "0");

    }


    private MutableLiveData<Image> mockImage() {
        Image image = new Image();
        image.setId(0);
        image.setImageURL(TEST_URL_0);

        MutableLiveData<Image> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.postValue(image);
        return mutableLiveData;
    }

}