package com.Picasso.controller;

import javax.servlet.http.HttpServletRequest;

import com.Picasso.entity.Account;
import com.Picasso.entity.Task;
import com.Picasso.entity.UserInfo;
import com.Picasso.service.TaskService;
import com.Picasso.service.UserService;
import com.Picasso.util.HashProtection;
import com.Picasso.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TestAPI {
  @Autowired
  private UserService userService;
  @Autowired
  private TaskService taskService;

  private SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private SimpleDateFormat birthday = new SimpleDateFormat("yyyy-MM-dd");

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public Map<String, Object> doLogin(@RequestBody Map<String, Object> info) {
    // {"username":"dzb","password":"123"}
    String username = (String) info.get("username");
    String password = (String) info.get("password");
    System.out.println(username + " " + password);
    Account account = userService.checkAccount(username, HashProtection.sha1(password));
    Map<String, Object> map = new HashMap<>();
    if (account != null) {
      map.put("success", true);
      map.put("info", account);
    } else {
      map.put("success", false);
    }
    return map;
  }

  @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
  public Map<String, Object> addAccount(@RequestBody Map<String, Object> info) {
    // {"username":"dzb","password":"123"}
    Map<String, Object> account = (Map<String, Object>) info.get("info");
    Map<String, Object> res = new HashMap<>();
    Map<String, Object> msg = (Map<String, Object>) info.get("msg");
    Account acc;
    if ((acc = userService.checkAccount((String) account.get("username"), (String) account.get("password"))) != null) {
      if (acc.getWeight() > 0) {
        int weight = 0;
        if (info.get("weight") != null) {
          weight = Integer.valueOf((String) msg.get("weight"));
        }
        System.out.println(weight);
        String init_pass = RandomString.getRandomString(10);
        boolean flag = userService.createAccount((String) msg.get("username"), HashProtection.sha1(init_pass), weight);
        if (!flag) {
          res.put("success", false);
          // res.put("info", acc);
          res.put("res", "double");
        } else {
          Account account1 = userService.findAccountByUserName((String) msg.get("username"));
          res.put("success", true);
          res.put("msg", account1);
          res.put("initPass", init_pass);
          // res.put("info", acc);
        }
      }
    } else {
      res.put("success", false);
      res.put("info", account);
      res.put("res", "weight");
    }
    return res;
  }

  @RequestMapping(value = "/delAccount", method = RequestMethod.POST)
  public Map<String, Object> delAccount(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (account.getWeight() > 0) {
        boolean flag = userService.delAccount(Integer.valueOf((String) msg.get("uid")));
        if (flag) {
          response.put("success", true);
        } else {
          response.put("success", false);
          response.put("res", "none");
        }
      } else {
        response.put("success", false);
        response.put("res", "weight");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/changePass", method = RequestMethod.POST)
  public Map<String, Object> changePass(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    int uid = Integer.valueOf((String) request.get("uid"));
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (account.getWeight() > 0 || account.getUid() == uid) {
        Account newaccount = userService.changPass(uid, HashProtection.sha1((String) msg.get("password")));
        if (newaccount != null) {
          response.put("success", true);
          if (account.getUid() == uid) {
            response.put("islogout", true);
          } else {
            response.put("islogout", false);
          }
        } else {
          response.put("success", false);
          response.put("res", "none");
        }
      } else {
        response.put("success", false);
        response.put("res", "weight");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
  public Map<String, Object> changeInfo(@RequestBody Map<String, Object> request) throws ParseException {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    int uid = Integer.valueOf(request.get("uid").toString());
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (account.getWeight() > 0 || account.getUid() == uid) {
        String name = (String) msg.get("name");
        String school = (String) msg.get("school");
        String profession = (String) msg.get("profession");
        String phone = (String) msg.get("phone");
        int sex = Integer.valueOf((Integer) msg.get("sex"));
        Date birth;
        if (msg.get("birth") != null)
          birth = birthday.parse((String) msg.get("birth"));
        else
          birth = birthday.parse("2000-01-01");
        UserInfo userinfo = userService.changeUserInfo(uid, name, school, profession, phone, sex, birth);
        if (userinfo != null) {
          response.put("success", true);
        } else {
          response.put("success", false);
          response.put("res", "notfound");
        }
      } else {
        response.put("success", false);
        response.put("res", "weight");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/addTask", method = RequestMethod.POST)
  public Map<String, Object> addTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      String title = (String) msg.get("title");
      String content = (String) msg.get("content");
      Date init_time = new Date();
      int process = 0;
      Task task = taskService.addTask(account.getUid(), title, content, init_time, init_time, process);
      if (task != null) {
        response.put("success", true);
      } else {
        response.put("success", false);
        response.put("res", "notfound");
      }

    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
  public Map<String, Object> updateTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    int tid = Integer.valueOf((String) request.get("tid"));
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (taskService.selectUserTask(account.getUid(), tid) != null) {
        int process = Integer.valueOf((String) msg.get("process"));
        Date update_time = new Date();
        Task task = taskService.selectTaskById(tid);
        if (task != null) {
          Task task1 = taskService.changeTask(task.getTitle(), task.getContent(), task.getInit_time(), update_time,
              process, tid);
          if (task1 == null) {
            response.put("success", false);
            response.put("res", "error");
          } else {
            response.put("success", true);
          }
        } else {
          response.put("success", false);
          response.put("res", "error");
        }
      } else {
        response.put("success", false);
        response.put("res", "error");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/changeTask", method = RequestMethod.POST)
  public Map<String, Object> changeTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Map<String, Object> msg = (Map<String, Object>) request.get("msg");
    int tid = Integer.valueOf((String) request.get("tid"));
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (taskService.selectUserTask(account.getUid(), tid) != null) {
        String title = (String) msg.get("title");
        String content = (String) msg.get("content");
        Task task = taskService.selectTaskById(tid);
        if (task != null) {
          Task task1 = taskService.changeTask(title, content, task.getInit_time(), task.getUpdate_time(),
              task.getProcess(), tid);
          if (task1 == null) {
            response.put("success", false);
            response.put("res", "error");
          } else {
            response.put("success", true);
          }
        } else {
          response.put("success", false);
          response.put("res", "error");
        }
      } else {
        response.put("success", false);
        response.put("res", "error");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/delTask", method = RequestMethod.POST)
  public Map<String, Object> delTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    int tid = Integer.valueOf((String) request.get("tid"));
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (taskService.selectUserTask(account.getUid(), tid) != null) {
        boolean flag = taskService.delTask(tid);
        if (flag) {
          response.put("success", true);
        } else {
          response.put("success", false);
          response.put("res", "error");
        }
      } else {
        response.put("success", false);
        response.put("res", "error");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/getTask", method = RequestMethod.POST)
  public Map<String, Object> getTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    int tid = Integer.valueOf((String) request.get("tid"));
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (taskService.selectUserTask(account.getUid(), tid) != null) {
        Task task = taskService.selectTaskById(tid);
        if (task != null) {
          response.put("success", true);
          response.put("msg", task);
        } else {
          response.put("success", false);
          response.put("res", "error");
        }
      } else {
        response.put("success", false);
        response.put("res", "error");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/getUserAllTask", method = RequestMethod.POST)
  public Map<String, Object> getUserAllTask(@RequestBody Map<String, Object> request) {
    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      response.put("success", true);
      response.put("msg", taskService.selectAllTaskByUid(account.getUid()));
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
  public Map<String, Object> getUsers(@RequestBody Map<String, Object> request) {

    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (account.getWeight() > 0) {
        response.put("success", true);
        List<Account> accountList = userService.selectAllAccountByWeight(0);
        List<UserInfo> userInfos = new ArrayList<>();
        for (Account account1 : accountList) {
          UserInfo userinfo = userService.showUserInfo(account1.getUid());
          if (userinfo != null) {
            userInfos.add(userinfo);
          } else {
            userInfos.add(new UserInfo(account1.getUid(), null, null, null, null, 0, null));
          }
        }
        response.put("msg", userInfos);
      } else {
        response.put("success", false);
        response.put("res", "weight");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

  @RequestMapping(value = "/getUser", method = RequestMethod.POST)
  public Map<String, Object> getUser(@RequestBody Map<String, Object> request) {

    Map<String, Object> response = new HashMap<>();
    Map<String, Object> acc = (Map<String, Object>) request.get("info");
    String s_uid = request.get("uid").toString();
    Account account;
    if ((account = userService.checkAccount((String) acc.get("username"), (String) acc.get("password"))) != null) {
      if (account.getWeight() > 0) {
        response.put("success", true);
        List<Account> accountList = userService.selectAllAccountByWeight(0);
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userinfo = userService.showUserInfo(Integer.parseInt(s_uid));
        if (userinfo != null)
          userInfos.add(userinfo);

        response.put("msg", userInfos);
      } else {
        response.put("success", false);
        response.put("res", "weight");
      }
    } else {
      response.put("success", false);
      response.put("res", "error");
    }
    return response;
  }

}