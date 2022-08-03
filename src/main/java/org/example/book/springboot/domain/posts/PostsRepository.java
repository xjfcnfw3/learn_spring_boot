package org.example.book.springboot.domain.posts;

import com.sun.org.apache.bcel.internal.generic.L2D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p order by  p.id DESC")
    List<Posts> findAllDesc();
}
