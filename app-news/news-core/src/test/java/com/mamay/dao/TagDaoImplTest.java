package com.mamay.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mamay.TestHolder;
import com.mamay.entity.TagEntity;
import com.mamay.exception.NewsException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@Sql(value = {"/dbunit/tag-data.xml", "/dbunit/news_tag-data.xml"})
public class TagDaoImplTest {

  @Autowired private TagDao tagDao;

  @Test
  public void loadAll() throws NewsException {
    List<TagEntity> tagList = tagDao.loadAll();
    assertEquals(20, tagList.size());
  }

  @Test
  public void loadByNewsId() throws NewsException {
    List<TagEntity> tagList = tagDao.loadByNewsId(Long.valueOf(3));
    assertEquals(3, tagList.size());
  }

  @Test
  public void loadById() throws NewsException {
    TagEntity actualEntity = tagDao.loadById(Long.valueOf(7));
    assertNotNull(actualEntity);
    assertEquals(Long.valueOf(7), actualEntity.getId());
  }

  @Test
  public void create() throws NewsException {
    TagEntity entity = TestHolder.generateTag();
    tagDao.create(entity);
    List<TagEntity> actualList = tagDao.loadAll();
    assertEquals(21, actualList.size());
  }

  @Test
  public void update() throws NewsException {
    TagEntity entity = TestHolder.generateTag();
    entity.setId(7L);
    tagDao.update(entity);
    TagEntity actualEntity = tagDao.loadById(Long.valueOf(7));
    assertEntityEquals(entity, actualEntity);
  }

  @Test
  public void delete() throws NewsException {
    tagDao.delete(Long.valueOf(100));
    List<TagEntity> actualList = tagDao.loadAll();
    assertEquals(20, actualList.size());
  }

  private void assertEntityEquals(TagEntity expected, TagEntity actual) {
    Assertions.assertEquals(expected.getName(), actual.getName());
  }
}
