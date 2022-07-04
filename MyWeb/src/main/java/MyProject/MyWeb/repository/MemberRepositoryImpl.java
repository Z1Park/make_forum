package MyProject.MyWeb.repository;

import MyProject.MyWeb.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepositoryImpl implements MemberRepository{

    // Map 임시 사용 : 추후 DB 대체
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(String name, String userId) {
        Member member = new Member(++sequence, name, userId);
        store.put(member.getId(), member);
        log.info("signUp new member={}", member);
        return member;
    }

    @Override
    public void addPost(String userId, Long postOrder) {
        Optional<Member> member = findByUserId(userId);
        if (member == null) {
            log.info("Posting fail : wrong userId");
            return;
        }
        member.get().addPost(postOrder);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        return findAll().stream().filter(m -> m.getUserId().equals(userId)).findFirst();
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void singOut(String id) {
        store.remove(id);
    }
}
