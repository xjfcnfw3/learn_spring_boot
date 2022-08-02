package org.example.book.springboot.domain.posts;

import com.sun.org.apache.bcel.internal.generic.L2D;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
