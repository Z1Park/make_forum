package MyProject.MyWeb.repository;

import MyProject.MyWeb.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(String name, String userId);

    void addPost(String userId, Long postOrder);

    Optional<Member> findByUserId(String userId);

    Member findById(Long id);

    List<Member> findAll();

    void singOut(String id);
}
