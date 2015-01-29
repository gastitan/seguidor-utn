package com.utn.adapter;

import com.utn.seguidor.R;
import com.utn.utils.Constantes;
import com.utn.vo.MateriaVO;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MateriaAdapter extends ArrayAdapter<MateriaVO> 
{
	private final int newsItemLayoutResource;
	 
	public MateriaAdapter(final Context context, final int newsItemLayoutResource) {
		super(context, 0);
		this.newsItemLayoutResource = newsItemLayoutResource;
	}
 
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) 
	{
		final View view = getWorkingView(convertView);
		final ViewHolder viewHolder = getViewHolder(view);
		final MateriaVO materia = getItem(position);
		
		viewHolder.titleView.setText(materia.getNombre());
		
		if(materia.getNombre().contains(Constantes.ANIO))
		{
			viewHolder.subTitleView.setVisibility(TextView.INVISIBLE);
			viewHolder.arrow.setVisibility(TextView.INVISIBLE);
			viewHolder.titleView.cancelLongPress();
			viewHolder.titleView.setGravity(Gravity.CENTER);
			view.setBackgroundColor(Color.TRANSPARENT);
			
		}else{
			final String formattedSubTitle = String.format("Estado: %s", materia.getEstado());
			viewHolder.subTitleView.setText(formattedSubTitle);
			viewHolder.titleView.setGravity(Gravity.LEFT);
			//viewHolder.titleView.setLongClickable(true);
			viewHolder.subTitleView.setVisibility(TextView.VISIBLE);
			
			if(!materia.isHabilitada()){
				view.setBackgroundColor(getContext().getResources().getColor(R.color.bloqueada));
			}else{
				view.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
			}
		}
		
		return view;
	}
 
	private View getWorkingView(final View convertView) {
		// The workingView is basically just the convertView re-used if possible
		// or inflated new if not possible
		View workingView = null;
		
		if(null == convertView) {
			final Context context = getContext();
			final LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			workingView = inflater.inflate(newsItemLayoutResource, null);
		} else {
			workingView = convertView;
		}
		
		return workingView;
	}
	
	private ViewHolder getViewHolder(final View workingView) {
		// The viewHolder allows us to avoid re-looking up view references
		// Since views are recycled, these references will never change
		final Object tag = workingView.getTag();
		ViewHolder viewHolder = null;
		
		
		if(null == tag || !(tag instanceof ViewHolder)) {
			viewHolder = new ViewHolder();
			
			viewHolder.titleView = (TextView) workingView.findViewById(R.id.nombre_materia);
			viewHolder.subTitleView = (TextView) workingView.findViewById(R.id.estado_materia);
			viewHolder.arrow = (TextView) workingView.findViewById(R.id.arrow);
			
			workingView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) tag;
		}
		
		return viewHolder;
	}
	
	/**
	 * ViewHolder allows us to avoid re-looking up view references
	 * Since views are recycled, these references will never change
	 */
	private static class ViewHolder {
		public TextView titleView;
		public TextView subTitleView;
		public TextView arrow;
	}
}
