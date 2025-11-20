package abod.obf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class ObfuscatorController {
 
	// Comment this out, will make the index page be accessible in the root 
  // @GetMapping("/")
  // public String home() {
  //   return "index";
  // }


  @PostMapping("/encode-py")
  public String obfPy(@RequestBody String input) {
    return Tricks.encode_Py(input);
  }


  @PostMapping("/encode-ja")
  public String obfJa(@RequestBody String input) {
    return Tricks.encode_Ja(input);
  }


  @PostMapping("/encode-js")
  public String obfJs(@RequestBody String input) {
    return Tricks.encode_Js(input);
  }


  @PostMapping("/encode-c")
  public String obfC(@RequestBody String input) {
    return Tricks.encode_C(input);
  }


  @PostMapping("/encode-cs")
  public String obfCs(@RequestBody String input) {
    return Tricks.encode_Cs(input);
  }


  @PostMapping("/encode-ru")
  public String obfRu(@RequestBody String input) {
	return Tricks.encode_Ru(input);
  }


 @PostMapping("/encode-go")
  public String obfGo(@RequestBody String input) {
	return Tricks.encode_Go(input);
  }
  
  
  @PostMapping("/encode-da")
  public String obfDa(@RequestBody String input) {
  return Tricks.encode_Da(input);
  }


  @PostMapping("/encode-cpp")
  public String obfCpp(@RequestBody String input) {
    return Tricks.encode_Cpp(input);
  }


  @PostMapping("/encode-rt")
  public String obfRust(@RequestBody String input) {
	return Tricks.encode_Rust(input);	  
  }	
}
