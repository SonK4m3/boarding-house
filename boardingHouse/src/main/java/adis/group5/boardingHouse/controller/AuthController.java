package adis.group5.boardingHouse.controller;

import adis.group5.boardingHouse.dao.MemberDAO;
import adis.group5.boardingHouse.model.Member;
import adis.group5.boardingHouse.model.response.DataResponse;
import adis.group5.boardingHouse.model.response.LoginRequest;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    private final Gson gson = new Gson();
    private final MemberDAO memberDAO = new MemberDAO();

    @GetMapping("hello/{name}")
    public String greeting(@PathVariable String name) {
        return "hello " + name;
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        LoginRequest loginRequest = gson.fromJson(body, LoginRequest.class);
        Member member = Member.createMemberLogin(loginRequest.getUsername(), loginRequest.getPassword());

        boolean success = memberDAO.checkLogin(member);

        if (success) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", member)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Wrong username or password", null)), HttpStatus.OK);
        }
    }
}
