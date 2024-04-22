package com.zja.dao.example;

import com.zja.entitys.example.ExampleUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @DataJpaTest 每次运行测试时，都会使用新的、干净的数据库，并且在测试完成后会自动回滚更改。
 * <p>
 * 使用了@DataJpaTest注解，可能导致了数据不准确的问题:
 * 由于@DataJpaTest使用嵌入式数据库，默认情况下不会将数据写入实际的数据库，而是将其存储在内存中。这可能导致在测试中更新数据后，再次查询时获取的数据仍然是之前的值，而不是预期的更新后的值。
 * </p>
 * @author: zhengja
 * @since: 2024/04/22 10:25
 */
// @SpringBootTest
@DataJpaTest // 默认采用内存库：h2
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 采用业务库：mysql等
public class ExampleUserRepoTests {

    @Autowired
    private ExampleUserRepo exampleUserRepo;

    @Test
    public void testFindByLoginName() {
        ExampleUser user = new ExampleUser();
        user.setLoginName("john123");
        user.setName("John Doe");
        exampleUserRepo.save(user);

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("John Doe", result.get().getName());
    }

    @Test
    public void testFindNameByLoginName() {
        ExampleUser user = new ExampleUser();
        user.setLoginName("jane456");
        user.setName("Jane Smith");
        exampleUserRepo.save(user);

        Optional<String> result = exampleUserRepo.findNameByLoginName("jane456");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Jane Smith", result.get());
    }

    @Test
    public void testFindByLoginNameIn() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("user1");
        user1.setName("User 1");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("user2");
        user2.setName("User 2");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        List<ExampleUser> result = exampleUserRepo.findByLoginNameIn(Arrays.asList("user1", "user2"));
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testFindByNameLike() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        List<ExampleUser> result = exampleUserRepo.findByNameLike("%John%");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testCountByName() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        long count = exampleUserRepo.countByName("John Doe");
        Assertions.assertEquals(1, count);
    }

    @Test
    public void testFindByNameWithPagination() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<ExampleUser> pageResult = exampleUserRepo.findByName("John Doe", pageable);

        Assertions.assertEquals(1, pageResult.getTotalElements());
        Assertions.assertEquals(1, pageResult.getTotalPages());
        List<ExampleUser> users = pageResult.getContent();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    public void testFindByKeywordOrderByAgeAscCreateTimeDesc() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "age").and(Sort.by(Sort.Direction.DESC, "createTime")));
        Page<ExampleUser> pageResult = exampleUserRepo.findByKeywordOrderByAgeAscCreateTimeDesc("John", pageable);

        Assertions.assertEquals(1, pageResult.getTotalElements());
        Assertions.assertEquals(1, pageResult.getTotalPages());
        List<ExampleUser> users = pageResult.getContent();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    public void testUpdateNameByLoginName() {
        ExampleUser user = new ExampleUser();
        user.setLoginName("john123");
        user.setName("John Doe");
        exampleUserRepo.save(user);

        exampleUserRepo.updateNameByLoginName("John Smith", "john123");

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("John Smith", result.get().getName()); // todo 没解决，使用了@DataJpaTest注解，可能导致了数据不准确的问题
    }

    @Test
    public void testDeleteByLoginName() {
        ExampleUser user = new ExampleUser();
        user.setLoginName("john123");
        user.setName("John Doe");
        exampleUserRepo.save(user);

        exampleUserRepo.deleteByLoginName("john123");

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteNameByLoginName() {
        ExampleUser user = new ExampleUser();
        user.setLoginName("john123");
        user.setName("John Doe");
        exampleUserRepo.save(user);

        exampleUserRepo.deleteNameByLoginName("john123");

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertNull(result.get().getName()); // todo 没解决，使用了@DataJpaTest注解，可能导致了数据不准确的问题
    }

    @Test
    public void testDeleteByName() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        long count = exampleUserRepo.deleteByName("John Doe");
        Assertions.assertEquals(1, count);

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testRemoveByName() {
        ExampleUser user1 = new ExampleUser();
        user1.setLoginName("john123");
        user1.setName("John Doe");

        ExampleUser user2 = new ExampleUser();
        user2.setLoginName("jane456");
        user2.setName("Jane Smith");

        exampleUserRepo.saveAll(Arrays.asList(user1, user2));

        List<ExampleUser> removedUsers = exampleUserRepo.removeByName("John Doe");
        Assertions.assertEquals(1, removedUsers.size());
        Assertions.assertEquals("John Doe", removedUsers.get(0).getName());

        Optional<ExampleUser> result = exampleUserRepo.findByLoginName("john123");
        Assertions.assertFalse(result.isPresent());
    }
}
