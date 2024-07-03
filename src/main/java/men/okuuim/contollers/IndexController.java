package men.okuuim.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @CrossOrigin разрешение проблемы Cross-Origin Request Blocked
 * @see <a href="https://habr.com/ru/companies/macloud/articles/553826/"> </a>/>
 *
 * решается на стороне фронта проксированием запросов
 */

//todo Это проверочный контроллер из-за ошибки Cross-Origin Request Blocked создал его что бы полностью проверить
// todo далее этот контроллер будет обраатывать стартовые запросы типа / или без него
//@CrossOrigin
@RestController
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String slash() {
        return "test";
    }

}
