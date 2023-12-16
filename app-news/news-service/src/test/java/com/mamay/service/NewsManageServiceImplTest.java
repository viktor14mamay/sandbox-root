package com.mamay.service;

import com.mamay.TestHolder;
import com.mamay.dao.AuthorDao;
import com.mamay.dao.NewsDao;
import com.mamay.dao.TagDao;
import com.mamay.entity.NewsEntity;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NewsManageServiceImplTest {

    @InjectMocks
    private static NewsManagementService newsManageService;

    @Mock
    private TagDao tagService;
    @Mock
    private AuthorDao authorService;
    @Mock
    private NewsService newsService;
    @Mock
    private NewsDao newsDao;

    @Test
    public void createWithTagsAndAuthor() {
        NewsEntity actualEntity = TestHolder.generateNewsEntity();
        doReturn(actualEntity.getId()).when(newsDao).create(actualEntity);
        List<Long> unmodifiableList = Arrays.asList(ArrayUtils.toObject(new long[]{7, 8, 9, 10, 11}));
        List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
        tagIdList.add(null);
        Long authorId = Long.valueOf(12);
        Long id = newsManageService.create(actualEntity, tagIdList, authorId);

        verify(authorService).loadById(authorId);
        verify(newsDao).create(actualEntity);
    }

    @Test
    public void updateWithTagsAndAuthor() {
        NewsEntity actualEntity = TestHolder.generateNewsEntity();
        actualEntity.setVersion(1);
        List<Long> unmodifiableList = Arrays.asList(ArrayUtils.toObject(new long[]{7, 8, 9, 10, 11}));
        List<Long> tagIdList = new ArrayList<Long>(unmodifiableList);
        tagIdList.add(null);
        Long authorId = Long.valueOf(12);

        newsManageService.update(actualEntity, tagIdList, authorId);

        verify(authorService).loadById(authorId);
        verify(newsDao).update(actualEntity);
    }
}
