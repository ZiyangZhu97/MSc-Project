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
    <div class="row">
      <div v-for="program in programs" class="col-md-4">
        <div class="thumbnail search-thumbnail">
          <img v-show="!program.image" class="media-object" src="/static/image/demo-program.jpg" />
          <img v-show="program.image" class="media-object" v-bind:src="program.image" />
          <div class="caption">
            <div class="clearfix">
              <span class="pull-right label label-primary info-label">
                {{PROGRAM_TYPE | optionKV(program.type)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{PROGRAM_CHARGE | optionKV(program.charge)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{PROGRAM_STATUS | optionKV(program.status)}}
              </span>
            </div>

            <h3 class="search-title">
              <a href="#" class="blue">{{program.title}}</a>
            </h3>
            <p>
              <span class="blue bolder bigger-150">{{program.price}}&nbsp;<i class="fa fa-gbp"></i></span>&nbsp;
            </p>
            <p>{{program.summary}}</p>
            <p>
              <span class="badge badge-info">{{program.id}}</span>
              <span class="badge badge-info">Order：{{program.sort}}</span>
              <span class="badge badge-info">Length：{{program.length}}</span>
            </p>
            <p>
              <button v-on:click="toChapter(program)" class="btn btn-white btn-xs btn-info btn-round">
                Season
              </button>&nbsp;
              <button v-on:click="edit(program)" class="btn btn-white btn-xs btn-info btn-round">
                Edit
              </button>&nbsp;
              <button v-on:click="del(program.id)" class="btn btn-white btn-xs btn-warning btn-round">
                Delete
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>
<!--    <table id="simple-table" class="table  table-bordered table-hover">-->
<!--      <thead>-->
<!--      <tr>-->
<!--        <th>ID</th>-->
<!--        <th>Title</th>-->
<!--        <th>Summary</th>-->
<!--        <th>Length (Seconds)</th>-->
<!--        <th>Price (GBP)</th>-->
<!--        <th>Cover</th>-->
<!--        <th>Type</th>-->
<!--        <th>Charge</th>-->
<!--        <th>Status</th>-->
<!--        <th>Number of Favorites</th>-->
<!--        <th>Order</th>-->
<!--        <th>Operation</th>-->
<!--      </tr>-->
<!--      </thead>-->

<!--      <tbody>-->
<!--      <tr v-for="program in programs">-->
<!--        <td>{{program.id}}</td>-->
<!--        <td>{{program.title}}</td>-->
<!--        <td>{{program.summary}}</td>-->
<!--        <td>{{program.length}}</td>-->
<!--        <td>{{program.price}}</td>-->
<!--        <td>{{program.image}}</td>-->
<!--        <td>{{PROGRAM_TYPE | optionKV(program.type)}}</td>-->
<!--        <td>{{PROGRAM_CHARGE | optionKV(program.charge)}}</td>-->
<!--        <td>{{PROGRAM_STATUS | optionKV(program.status)}}</td>-->
<!--        <td>{{program.favorites}}</td>-->
<!--        <td>{{program.sort}}</td>-->
<!--      <td>-->
<!--        <div class="hidden-sm hidden-xs btn-group">-->
<!--          <button v-on:click="edit(program)" class="btn btn-xs btn-info">-->
<!--            <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--          </button>-->
<!--          <button v-on:click="del(program.id)" class="btn btn-xs btn-danger">-->
<!--            <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--          </button>-->
<!--        </div>-->
<!--      </td>-->
<!--      </tr>-->
<!--      </tbody>-->
<!--    </table>-->

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
                  <input v-model="program.title" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Summary</label>
                <div class="col-sm-10">
                  <input v-model="program.summary" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Length (Seconds)</label>
                <div class="col-sm-10">
                  <input v-model="program.length" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Price (GBP)</label>
                <div class="col-sm-10">
                  <input v-model="program.price" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Cover</label>
                <div class="col-sm-10">
                  <input v-model="program.image" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Type</label>
                <div class="col-sm-10">
                  <select v-model="program.type" class="form-control">
                    <option v-for="o in PROGRAM_TYPE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Charge</label>
                <div class="col-sm-10">
                  <select v-model="program.charge" class="form-control">
                    <option v-for="o in PROGRAM_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Status</label>
                <div class="col-sm-10">
                  <select v-model="program.status" class="form-control">
                    <option v-for="o in PROGRAM_STATUS" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Number of Favorites</label>
                <div class="col-sm-10">
                  <input v-model="program.favorites" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">Order</label>
                <div class="col-sm-10">
                  <input v-model="program.sort" class="form-control">
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
    name: "business-program",
    data: function() {
      return {
        program: {},
        programs: [],
        PROGRAM_TYPE: PROGRAM_TYPE,
        PROGRAM_CHARGE: PROGRAM_CHARGE,
        PROGRAM_STATUS: PROGRAM_STATUS,
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("business-program-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.program = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(program) {
        let _this = this;
        _this.program = $.extend({}, program);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/program/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          let resp = response.data;
          _this.programs = resp.content.list;
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
          || !Validator.require(_this.program.title, "Title")
          || !Validator.length(_this.program.title, "Title", 1, 50)
          || !Validator.length(_this.program.summary, "Summary", 1, 2000)
          || !Validator.length(_this.program.image, "Cover", 1, 100)
        ) {
          return;
        }

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/program/save', _this.program).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/program/delete/' + id).then((response)=>{
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("Your file has been deleted!");
            }
          })
        });
      },
      /**
       * 点击【大章】
       */
      toChapter(program) {
        let _this = this;
        SessionStorage.set("program", program);
        _this.$router.push("/business/chapter");
      }
    }
  }
</script>

<style scoped>
  .caption h3 {
    font-size: 20px;
  }
</style>