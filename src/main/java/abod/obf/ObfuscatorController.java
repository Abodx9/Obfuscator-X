package abod.obf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObfuscatorController {
  @GetMapping("/")
  public String main() {
    return "Hey! I am the Obfuscator!";
  }
}
