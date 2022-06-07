# Civilization-group13
Aryan Ansari 400104761

Sobhan Asadi 400170306

Mohammad Lotfi 400105228

Mahdi Mohammadi 400105239

-------------------------------------------------------------------
![photo_2022-04-22_14-11-45](https://user-images.githubusercontent.com/94686711/167360462-e4aff310-ad24-464f-8325-80c375ca3ea9.jpg)


**instruction to Play Game**

- **Run the game**: to Play the Game run src/main/java/Main.java
 
- **Commands**

  - Login Menu:
    -  Register: ***user register --username     "username"     --nickname     "nickname"     --password     "password"***
    -  Register as shorter: ***user register -u "username" -n "nickname" -p "password"***
    -  Login: ***user login --username "username" --password "password"***
    -  Login as shorter: ***user login -u "username" -p "password"***
    -  show current menu: ***menu show-current***
    -  go to next menu: ***menu enter "Menu name(Main Menu)"***
    -  exit: ***menu exit***
  - Main Menu:
    -  back: ***menu exit***
    -  show current menu: ***menu show-current***
    -  logout: ***user logout***
    -  go to next menu: ***menu enter "Menu name(Profile Menu|Game Menu)"***
  - Profile Menu:
    -  back: ***menu exit***
    -  show current menu: ***menu show-current***
    -  go to next menu: ***menu enter "Menu name"***
    -  change nick name: ***profile change --nickname "new nickname"***
    -  change nick name as shorter: ***profile change -n "new nickname"***
    -  change password: ***profile change password --current "current password" --new "new password"***
    -  change password as shorter: ***profile change password -c "current password" -n "new password"***
  - Game Menu:
    -  back: ***menu exit***
    -  show current menu: ***menu show-current***
    -  go to next menu: ***menu enter "Menu name"***
    -  to show map: ***show map***
    -  select city: ***select city --coordinates -x "x pos" -y "y pos"***
    -  select city as shorter: ***select city -c -x "x pos" -y "y pos"***
