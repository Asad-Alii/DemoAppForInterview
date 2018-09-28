package com.asad.demoappforinterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    RecyclerView rvItem;

    private RequestQueue requestQueue;

    private ArrayList<Product> productlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItem = (RecyclerView)findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvItem.setLayoutManager(manager);

        productlist = new ArrayList<>();


        String url = "http://192.168.1.103:8080/students.php";
        JsonArrayRequest jsonArrayRequest  = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response);

                        /*ArrayList<Product> productList = new JsonConverter<Product>()
                                .toArrayList(response, Product.class);

                        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), productList);

                        rvItem.setAdapter(adapter);*/

                        for (int i = 0; i < response.length(); i++) {
                            //Creating the superhero object
                            Product product = new Product();
                            JSONObject json = null;
                            try {
                                //Getting json
                                json = response.getJSONObject(i);

                                //Adding data to the superhero object
                                product.setId(json.getString("id"));
                                product.setName(json.getString("name"));
                                product.setFatherName(json.getString("fatherName"));
                                product.setAddress(json.getString("address"));
                                product.setDateOfBirth(json.getString("dateOfBirth"));
                                product.setDateOfAdmission(json.getString("dateOfAdmission"));
                                product.setGrade(json.getString("grade"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Adding the superhero object to the list
                            productlist.add(product);
                        }

                        //Notifying the adapter that data has been added or changed
                        //adapter.notifyDataSetChanged();

                        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), productlist);

                        rvItem.setAdapter(adapter);

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);

        //requestQueue.add(jsonArrayRequest);


    }

//    private ArrayList<Item> generatedDummy(){
//        ArrayList<Item> list = new ArrayList<>();
//        for(int i = 0; i < 50; i++){
//            if(i % 3 == 0){
//                Item item = new Item();
//                item.id = i;
//                item.text = "Angkor Wat " + i;
//                item.img = "https://lonelyplanetwp.imgix.net/2016/01/angkor-wat-with-water.jpg";
//                list.add(item);
//            }
//            else if(i % 7 == 0){
//                Item item = new Item();
//                item.id = i;
//                item.text = "Bayon " + i;
//                item.img = "http://www.livingif.com/wp-content/gallery/bayon/bayon-cambodia-11.jpg";
//                list.add(item);
//            }
//            else if(i % 11 == 0){
//                Item item = new Item();
//                item.id = i;
//                item.text = "Something text " + i;
//                item.img = "http://www.aangkortourguide.com/images/cambodia_bayon.jpg";
//                list.add(item);
//            }
//            else{
//                Item item = new Item();
//                item.id = i;
//                item.text = "Dummy text " + i;
//                item.img = "http://www.worldwidetoursagency.com/wp-content/uploads/2016/01/phnom-penh-tour_sinhcafe-travel.jpg";
//                list.add(item);
//            }
//
//
//        }
//        return list;
//    }
}
