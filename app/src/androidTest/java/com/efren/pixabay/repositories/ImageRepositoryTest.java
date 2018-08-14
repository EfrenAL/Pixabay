package com.efren.pixabay.repositories;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.efren.pixabay.base.PixabayApi;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by efren.lamolda on 14.08.18.
 */
public class ImageRepositoryTest {


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private PixabayApi pixabayApi;

    private ImageRepository imageRepository;

    @Test
    public void getImages() throws Exception {
        //ToDo
    }

    @Test
    public void getImage() throws Exception {
        //ToDo
    }

}