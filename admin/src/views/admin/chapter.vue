<template>
  <div>
    <h4 class="lighter">
      <router-link to="/business/program" class="pink"> {{program.title}} </router-link>
    </h4>
    <hr>
    <p>
      <router-link to="/business/program" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-arrow-left"></i>
        Back to Program
      </router-link>
      &nbsp;
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
      <th>Title</th>
      <th>Operation</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="chapter in chapters">
      <td>{{chapter.id}}</td>
      <td>{{chapter.name}}</td>
      <td>
        <div class="hidden-sm hidden-xs btn-group">

          <button v-on:click="toEpisode(chapter)" class="btn btn-white btn-xs btn-info btn-round">
            Episode
          </button>&nbsp;
          <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
            Edit
          </button>&nbsp;
          <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
            Delete
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
                <label class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="Title">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Program</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{program.title}}</p>
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
    name: "chapter",
    data: function() {
      return {
        chapter: {},
        chapters: [],
        program: {},
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
            let program = SessionStorage.get("program") || {};
      if (Tool.isEmpty(program)) {
        _this.$router.push("/welcome");
      }
      _this.program = program;
      _this.list(1);
      // sidebar激活样式方法一
      this.$parent.activeSidebar("business-chapter-sidebar");

    },
    methods: {
      add() {
          let _this = this;
        _this.chapter = {};
        $("#form-modal").modal("show");
      },

      edit(chapter) {
        let _this = this;
        _this.chapter = $.extend({}, chapter);
          $(".modal").modal("show");
        },
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
          page: page,
          size: _this.$refs.pagination.size,
          programId: _this.program.id
        }).then((response)=>{
          console.log("查询大章列表结果：", response);
          let resp = response.data;
          _this.chapters = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      save(page) {
        let _this = this;
        // 保存校验
        if (!Validator.require(_this.chapter.name, "Title")
          || !Validator.length(_this.chapter.programId, "Program ID", 1, 8)) {
          return;
        }
        _this.chapter.programId = _this.program.id;

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save', _this.chapter).then((response)=>{
          console.log("保存大章列表结果：", response);
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

      del(id) {
        let _this = this;
        Confirm.show("You won't be able to revert this!", function () {

          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response)=>{

          console.log("删除大章列表结果：", response);
          let resp = response.data;
          if (resp.success) {
          _this.list(1);
          Toast.success("Your file has been deleted!");
          }
        })
      });
      },

      /**
       * 点击【小节】
       */
      toEpisode(chapter) {
        let _this = this;
        SessionStorage.set("chapter", chapter);
        _this.$router.push("/business/episode");

//        Swal.fire({
//          title: 'Are you sure',
//          text: "You won't be able to revert this!",
//          icon: 'warning',
//          showCancelButton: true,
//          confirmButtonColor: '#3085d6',
//          cancelButtonColor: '#d33',
//          confirmButtonText: 'Yes, delete it!'
//        }).then((result) => {
//          if (result.value) {
//            _this.$ajax.delete('http://127.0.0.1:9000/business/admin/chapter/delete/' + id).then((response)=>{
//              console.log("删除大章列表结果：", response);
//              let resp = response.data;
//              if (resp.success) {
//                _this.list(1);
//                Toast.success("Your file has been deleted!");
//
//              }
//            })
//          }
//        })
      }
    }
  }
</script>