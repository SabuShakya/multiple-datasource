package com.sabu.multipledatasource.repository.readonly;

import com.sabu.multipledatasource.annotation.ReadOnlyRepository;
import com.sabu.multipledatasource.entity.common.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sabu Shakya
 * @email <sabu.shakya@f1soft.com>
 * @createdDate 2021/04/05
 */
@Repository
@ReadOnlyRepository
public interface BooksReadOnlyRepository extends JpaRepository<Books, Long> {

}
