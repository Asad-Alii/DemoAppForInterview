package com.asad.demoappforinterview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.product_cardview_layout, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.tvId.setText(product.getId());
        holder.tvName.setText(product.getName());
        holder.tvFatherName.setText(product.getFatherName());
        holder.tvAddress.setText(product.getAddress());
        holder.tvDateOfBirth.setText(product.getDateOfBirth());
        holder.tvDateOfAdmission.setText(product.getDateOfAdmission());
        holder.tvGrade.setText(product.getGrade());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isClick) {

                Intent in = new Intent(context, ProductEditActivity.class);
                view.getContext().startActivity(in);

                /*final AlertDialog.Builder dialog= new AlertDialog.Builder(context);
                dialog.setTitle("Edit Form");
                //dialog.setMessage("Please use 'email' to register");

                LayoutInflater inflater=LayoutInflater.from(context);
                View edit_layout=inflater.inflate(R.layout.product_edit_layout,null);

                dialog.setView(edit_layout);

                dialog.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(context, "Commit", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show();

                    }
                });

               dialog.create();*/

            }
        });

    }

    @Override
    public int getItemCount() {
        if(products != null){
            return products.size();
        }
        return 0;
    }


    //ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public CardView cvStudents;
        public TextView tvId;
        public TextView tvName;
        public TextView tvFatherName;
        public TextView tvAddress;
        public TextView tvDateOfBirth;
        public TextView tvDateOfAdmission;
        public TextView tvGrade;

        private ItemClickListener itemClickListener;

        public ProductViewHolder(View itemView) {
            super(itemView);
            cvStudents = (CardView)itemView.findViewById(R.id.cvStudents);
            tvId = (TextView)itemView.findViewById(R.id.tvId);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvFatherName = (TextView)itemView.findViewById(R.id.tvFatherName);
            tvAddress = (TextView)itemView.findViewById(R.id.tvAddress);
            tvDateOfBirth = (TextView)itemView.findViewById(R.id.tvDateOfBirth);
            tvDateOfAdmission = (TextView)itemView.findViewById(R.id.tvDateOfAdmission);
            tvGrade = (TextView)itemView.findViewById(R.id.tvGrade);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {

            itemClickListener.onClick(view, getAdapterPosition(), false);

        }

        @Override
        public boolean onLongClick(View view) {

            itemClickListener.onClick(view, getAdapterPosition(), true );
            return true;
        }
    }
}
