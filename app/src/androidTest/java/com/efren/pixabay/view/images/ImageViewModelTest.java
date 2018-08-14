package com.efren.pixabay.view.images;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.repositories.ImageRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by efren.lamolda on 14.08.18.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageViewModelTest {

    public static final String TEST_URL_0 = "testURL0";
    public static final String TEST_URL_1 = "testURL1";
    public static final String TEST_URL_2 = "testURL2";
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public ImageRepository imageRepository;

    private ImageViewModel imageViewModel;

    private ArrayList<Image> imagesArray;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        imageViewModel = new ImageViewModel(imageRepository);
        mockImages();
    }

    @Test
    public void getImages() throws Exception {

        MutableLiveData<ArrayList<Image>> imagesLiveData = new MutableLiveData<>();
        imagesLiveData.postValue(imagesArray);

        when(imageRepository.getImages("test")).thenReturn(imagesLiveData);

        MutableLiveData<ArrayList<Image>> images = imageViewModel.getImages("test");
        assertEquals(images.getValue().size(), 3);
        assertEquals(images.getValue().get(0).getImageURL(), TEST_URL_0);
        assertEquals(images.getValue().get(0).getId().toString(), "0");

        assertEquals(images.getValue().get(1).getImageURL(), TEST_URL_1);
        assertEquals(images.getValue().get(1).getId().toString(), "1");

        assertEquals(images.getValue().get(2).getImageURL(), TEST_URL_2);
        assertEquals(images.getValue().get(2).getId().toString(), "2");

    }


    private void mockImages() {
        imagesArray = new ArrayList<>();
        Image image = new Image();
        image.setId(0);
        image.setImageURL(TEST_URL_0);
        imagesArray.add(image);

        image = new Image();
        image.setId(1);
        image.setImageURL(TEST_URL_1);
        imagesArray.add(image);

        image = new Image();
        image.setId(2);
        image.setImageURL(TEST_URL_2);
        imagesArray.add(image);
    }

}