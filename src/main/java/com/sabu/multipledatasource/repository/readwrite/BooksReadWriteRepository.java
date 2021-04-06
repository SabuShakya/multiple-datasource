package com.sabu.multipledatasource.repository.readwrite;

import com.sabu.multipledatasource.entity.common.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@Repository
public interface BooksReadWriteRepository extends JpaRepository<Books,Long> {

}
