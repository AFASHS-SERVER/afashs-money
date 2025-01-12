# afashs-money

---

# 1. API
  * 간편하게 유저의 돈 데이터를 관리합니다.
  ```java
  import com.github.jaeukkang12.money.api.EconomyAPI;
  

  public class Main extends JavaPlugin {
      @Override
      public void onEnable() {
          Economy economy = EconomyAPI.getEconomy();
      }
  }
  ```