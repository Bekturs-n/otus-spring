<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button @click="fetchData">Get students</button>

    <div id="center">
      <table id="center">
        <tr v-for="(row, idx) in into" :key="idx">
          <td>{{ row.id }}</td>
          <td>{{ row.firstName }}</td>
          <td>{{ row.lastName }}</td>
          <td>
            <button @click="Setting">Edit</button>
          </td>
          <td>
            <button @click="Delete(row.id)">Delete</button>
          </td>
        </tr>
      </table>
    </div>


    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3>Installed CLI Plugins</h3>
    <ul>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel" target="_blank"
             rel="noopener">babel</a></li>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint" target="_blank"
             rel="noopener">eslint</a></li>
    </ul>
    <h3>Essential Links</h3>
    <ul>
      <li><a href="https://vuejs.org" target="_blank" rel="noopener">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank" rel="noopener">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank" rel="noopener">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank" rel="noopener">Twitter</a></li>
      <li><a href="https://news.vuejs.org" target="_blank" rel="noopener">News</a></li>
    </ul>
    <h3>Ecosystem</h3>
    <ul>
      <li><a href="https://router.vuejs.org" target="_blank" rel="noopener">vue-router</a></li>
      <li><a href="https://vuex.vuejs.org" target="_blank" rel="noopener">vuex</a></li>
      <li><a href="https://github.com/vuejs/vue-devtools#vue-devtools" target="_blank" rel="noopener">vue-devtools</a>
      </li>
      <li><a href="https://vue-loader.vuejs.org" target="_blank" rel="noopener">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank" rel="noopener">awesome-vue</a></li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  // todo вынести url в общий файл
  data: function () {
    return {
      into: null
    }
  },
  methods: {
    fetchData() {
      try {
        axios.get('/students/getAll').then((response) => {
          this.into = JSON.parse(JSON.stringify(response.data));
          console.log(this.into);
        })
      } catch (err) {
        console.error(err);
      }
    }
  }
};
// ,
// Delete(id) {
//   axios.get('/students/delete?id='+id);
//   console.log(id);
//   this.into.splice(id, 1);
//   // this.items.splice(index, 1);
// }
// this.info = response.data;
// console.log("------------------");
// console.log("------------------");
// console.log(typeof json);
// console.log(json[0]);
// console.log("------------------");
// console.log("------------------");
// console.log(typeof this.info);
// console.log("------------------");
// console.log(typeof response.data[0])
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

#center {
  margin: auto;
  width: 50%;
  padding: 10px;
}

table, th, td {
  padding: 20px;
}

td {
  font-size: 20px;
  color: black;
  font-weight: bold;
}
</style>
