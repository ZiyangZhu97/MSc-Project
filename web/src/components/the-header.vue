<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">
          <i class="ace-icon fa fa-video-camera"></i>&nbsp;ZZY Videos
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <router-link class="nav-link" to="/">Home</router-link>
            </li>
            <li class="nav-item active">
              <router-link class="nav-link" to="/list">All Programs</router-link>
            </li>
            <li class="nav-item dropdown active">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                More
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">about us</a>
                <a class="dropdown-item" href="#">cooperation</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">more information</a>
              </div>
            </li>
          </ul>
          <span class="text-white"></span>
          <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Login/Register</button>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>

  export default {
    name: 'theHeader',
  }
</script>
