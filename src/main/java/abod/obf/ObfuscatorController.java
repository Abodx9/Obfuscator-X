package abod.obf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ObfuscatorController {
 
  @GetMapping("/")
  public String main() {
    return "Hey! I am the Obfuscator!";
  }


  @GetMapping("/encode-py")
  public String obfPy(@RequestBody String input) {
    return Tricks.encode_Py(input);
  }


  @GetMapping("/encode-ja")
  public String obfJa(@RequestBody String input) {
    return Tricks.encode_Ja(input);
  }
}
