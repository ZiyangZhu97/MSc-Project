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
                <a class="dropdown-item" href="#">xxx</a>
                <a class="dropdown-item" href="#">xxxx</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">xxx</a>
              </div>
            </li>
          </ul>
          <span v-show="loginMember.id" class="text-white pr-3">Hello: {{loginMember.name}}</span>
          <button v-show="loginMember.id" v-on:click="logout()" class="btn btn-outline-light my-2 my-sm-0">Logout</button>
          <button v-show="!loginMember.id"  v-on:click="openLoginModal()" class="btn btn-outline-light my-2 my-sm-0" type="submit">Login/Register</button>
        </div>
      </div>
    </nav>
    <the-login ref="loginComponent"></the-login>
  </header>
</template>

<script>

  import TheLogin from "./login";
  export default {
    name: 'theHeader',
    components: {TheLogin},
        data: function () {
      return {
        loginMember: {}
      }
    },
    mounted() {
      let _this = this;
      _this.loginMember = Tool.getLoginMember();
    },
    methods: {
      /**
       * 打开登录注册窗口
       */
      openLoginModal() {
        let _this = this;
        _this.$refs.loginComponent.openLoginModal();
      },
      setLoginMember(loginMember) {
        let _this = this;
        _this.loginMember = loginMember;
      },

      logout () {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/member/logout').then((response)=>{
          let resp = response.data;
          if (resp.success) {
            Tool.setLoginMember(null);
            _this.loginMember = {};
            Toast.success("Logout succeeded!");
          } else {
            Toast.warning(resp.message);
          }
        });
      },

    }
  }
</script>