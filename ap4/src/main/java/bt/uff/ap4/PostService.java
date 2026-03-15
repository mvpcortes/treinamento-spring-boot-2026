package bt.uff.ap4;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService {

    private final Validator validator;

    private final PostRepository postRepository;

    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        post = post.withDataPostagem(LocalDateTime.now());

        final Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Objeto inválido: " + violations);
        }

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findObjById(long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post com id %d não encontrado".formatted(id)));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
