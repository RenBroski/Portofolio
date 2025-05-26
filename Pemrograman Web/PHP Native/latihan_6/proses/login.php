<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="style1.css">
  <title>Login Form Demo</title>
</head>
<body>
  <div class="login-wrapper">
    <form action="proses_login.php" method="POST" class="form">
      <img src="img/download.jpg" alt="">
      <h2>Login</h2>
      <div class="input-group">
        <input type="text" name="username" id="username" autocomplete="off" required>
        <label for="username">User Name</label>
      </div>
      <div class="input-group">
        <input type="password" name="password" id="password" required>
        <label for="password">Password</label>
      </div>
      <input type="submit" value="Login" class="submit-btn">
    </form>

    <div id="forgot-pw">
      <form action="" class="form">
        <a href="#" class="close">&times;</a>
        <h2>Reset Password</h2>
        <div class="input-group">
          <input type="email" name="email" id="email" required>
          <label for="email">Email</label>
        </div>
        <input type="submit" value="Submit" class="submit-btn">
      </form>
    </div>
  </div>
</body>
</html>