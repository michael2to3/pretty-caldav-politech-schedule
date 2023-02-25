package pretty.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env/")
class Prop {
  @Autowired
  private Environment env;

  @GetMapping("/source")
  @ResponseBody
  public String source() {
    return env.getProperty("application.source");
  }

  @GetMapping("/target")
  @ResponseBody
  public String target() {
    return env.getProperty("application.target");
  }

}
