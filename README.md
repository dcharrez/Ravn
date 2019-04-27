# Ravn
Ravn GraphQL Code Challenge

## Setting your github-api-key

1. (Optional) [How to get a github api key](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line)
2. Go to the `App's gradle` and replace `XXX-GITHUB-API-KEYXX` with your key

```
android {
    compileSdkVersion 28
    defaultConfig {

        buildConfigField 'String', 'github_api_key', "\"XXX-GITHUB-API-KEYXX\""
    }
```

## Screenshots

Looking for a github User | Showing his/her repositories
:-------------------------:|:-------------------------:
![](https://github.com/dcharrez/Ravn/blob/master/README_imgs/rvn3.jpg)  |  ![](https://github.com/dcharrez/Ravn/blob/master/README_imgs/rvn2.jpg)

## Architechture followed

<img src="https://github.com/dcharrez/Ravn/blob/master/README_imgs/Architechture.png"/>
