package com.amarted.furfamtrans1.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amarted.furfamtrans1.Model.Transaction;
import com.amarted.furfamtrans1.R;

import java.util.List;

/*
 *****    This is our Adapter to connect our RecyclerView to Our Database and ListRow (Model, Data, Layout=>row_trans.xml)
 */

public class TransAllViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Create a context -
    private Context context;
    private List<Transaction> transactionList;


    public TransAllViewAdapter(Context ctx, List<Transaction> transactionList) {
        this.context = ctx;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Create a view object called "view" and inflate => LayoutInflator.from(context_from.inflateLayout ViewGroup_passed_in, attachToRoot:)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trans_sum,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // this is where we bind the views with the objects
        Transaction transaction = transactionList.get(position);

        holder.port;

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // This is where we set up all of our widgets that we have in our row
        public ImageView vendorLogo;
        public TextView portion;
        protected TextView date;
        private TextView totalText;
        private TextView discountText;
        private TextView totalBalanceText;
        private int id;

//        vendorLogo = itemView.findViewById(R.layout.row_trans_sum);


        public ViewHolder(@NonNull View view,Context ctx) {
            super(view);
            // set overall context from first few lines to the whatever context we're passing in here
            context = ctx;

            //set up our widgets
            vendorLogo = view.findViewById(R.id.rowLogoID);
            portion = view.findViewById(R.id.rowPortionID);
            date = view.findViewById(R.id.rowDatePaidID);
            totalText = view.findViewById(R.id.rowTotalTextID);
            discountText = view.findViewById(R.id.rowDiscountTextID);
            totalBalanceText = view.findViewById(R.id.rowTotalBalanceTextID);
            //setup onClick listener(s)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next screen - detail view
                    //ToDo: Make detailViewActivity
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
