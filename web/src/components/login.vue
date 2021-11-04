<template>
  <div id="login-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-login" role="document">
      <div class="modal-content">
        <div class="modal-body">
          <div class="register-div">
            <h3>Register</h3>
            <div class="form-group">
              <input id="register-email" v-model="memberRegister.email"
                     class="form-control" placeholder="email">
            </div>

            <div class="form-group">
              <input id="register-name" v-model="memberRegister.name"
                     class="form-control" placeholder="name">
            </div>
            <div class="form-group">
              <input id="register-password" v-model="memberRegister.passwordOriginal"
                     class="form-control" placeholder="password" type="password">
            </div>
            <div class="form-group">
              <input id="register-confirm-password" v-model="memberRegister.confirm"
                     class="form-control" placeholder="confirm password"
                     name="memberRegisterConfirm" type="password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-block submit-button" v-on:click="register()">
                Register
              </button>
            </div>
            <div class="form-group to-login-div">
              <a href="javascript:;">I want to login</a>
            </div>
          </div>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
</template>

<script>

  export default {
    name: 'the-login',
    data: function () {
      return {
        memberRegister: {},

        rememberMe: true, // 记住密码
        imageCodeToken: ""
      }
    },
    mounted() {
      let _this = this;
    },
    methods: {

      /**
       * 打开登录注册窗口
       */
      openLoginModal() {
        let _this = this;
        $("#login-modal").modal("show");
      },

      register() {
        let _this = this;
        _this.memberRegister.password = hex_md5(_this.memberRegister.passwordOriginal + KEY);

        // 调服务端注册接口
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/member/register', _this.memberRegister).then((response) => {
          let resp = response.data;
          if (resp.success) {
            Toast.success("Registered successfully!");
          } else {
            Toast.warning(resp.message);
          }
        })
      },

    }
  }
</script>

<style scoped>
  /* 登录框 */
  .login-div .input-group-addon {
    padding: 0;
    border: 0;
  }

  #login-modal h3 {
    text-align: center;
    margin-bottom: 20px;
  }

  #login-modal .modal-login {
    max-width: 400px;
  }

  #login-modal input {
    height: 45px;
    font-size: 16px;
  }

  #login-modal .submit-button {
    height: 50px;
    font-size: 20px;
  }

  #login-modal .to-login-div {
    text-align: center;
  }
</style>
