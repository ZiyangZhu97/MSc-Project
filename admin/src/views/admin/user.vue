<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        Add
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        Refresh
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>Login Name</th>
        <th>Username</th>
        <th>Password</th>
        <th>Operation</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="user in users">
        <td>{{user.id}}</td>
        <td>{{user.loginName}}</td>
        <td>{{user.name}}</td>
        <td>{{user.password}}</td>
      <td>
        <div class="hidden-sm hidden-xs btn-group">
          <button v-on:click="edit(user)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>
          <button v-on:click="del(user.id)" class="btn btn-xs btn-danger">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>
        </div>
      </td>
      </tr>
      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Form</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">Login Name</label>
                <div class="col-sm-10">
                  <input v-model="user.loginName" v-bind:disabled="user.id" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                  <input v-model="user.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                  <input v-model="user.password" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">Save</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "system-user",
    data: function() {
      return {
        user: {},
        users: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-user-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.user = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(user) {
        let _this = this;
        _this.user = $.extend({}, user);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          let resp = response.data;
          _this.users = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save(page) {
        let _this = this;

        // 保存校验
        if (1 != 1
          || !Validator.require(_this.user.loginName, "Login Name")
          || !Validator.length(_this.user.loginName, "Login Name", 1, 50)
          || !Validator.length(_this.user.name, "Username", 1, 50)
          || !Validator.require(_this.user.password, "Password")
          || !Validator.length(_this.user.password, "Password", 8, 16)
        ) {
          return;
        }
        _this.user.password = hex_md5(_this.user.password + KEY);
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save', _this.user).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("Your file has been saved!");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("You won't be able to revert this!", function () {
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/user/delete/' + id).then((response)=>{
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("Your file has been deleted!");
            }
          })
        });
      }
    }
  }
</script>