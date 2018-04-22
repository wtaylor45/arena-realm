package com.titosworld.gladius.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSanguine extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer arm1;
	public ModelRenderer arm2;
	private final ModelRenderer[] sticks = new ModelRenderer[12];
	
	public ModelSanguine() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0f);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0f);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arm1 = new ModelRenderer(this, 40,16);
        this.arm1.addBox(-3.0f, -2.0F, -2.0f,  4, 12, 4);
        this.arm1.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.arm2 = new ModelRenderer(this, 40,16);
        this.arm2.addBox(-3.0f, -2.0F, -2.0f,  4, 12, 4);
        this.arm2.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0f);
        this.leg1.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0f);
        this.leg2.setRotationPoint(1.9F, 12.0F, 0.0F);
        
        for (int i = 0; i < this.sticks.length; ++i)
        {
            this.sticks[i] = new ModelRenderer(this, 32, 0);
            this.sticks[i].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
        }
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale){
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.head.render(scale);
        this.body.render(scale);
        this.arm1.render(scale);
        this.arm2.render(scale);
        this.leg1.render(scale);
        this.leg2.render(scale);
        
        for(ModelRenderer stick : sticks) {
        	stick.render(scale);
        }
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.arm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.arm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        
        float f = ageInTicks * (float)Math.PI * -0.1F;

        for (int i = 0; i < 4; ++i)
        {
            this.sticks[i].rotationPointY = -2.0F + MathHelper.cos(((float)(i * 2) + ageInTicks) * 0.25F);
            this.sticks[i].rotationPointX = MathHelper.cos(f) * 9.0F;
            this.sticks[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
            ++f;
        }

        f = ((float)Math.PI / 4F) + ageInTicks * (float)Math.PI * 0.03F;

        for (int j = 4; j < 8; ++j)
        {
            this.sticks[j].rotationPointY = 2.0F + MathHelper.cos(((float)(j * 2) + ageInTicks) * 0.25F);
            this.sticks[j].rotationPointX = MathHelper.cos(f) * 7.0F;
            this.sticks[j].rotationPointZ = MathHelper.sin(f) * 7.0F;
            ++f;
        }

        f = 0.47123894F + ageInTicks * (float)Math.PI * -0.05F;

        for (int k = 8; k < 12; ++k)
        {
            this.sticks[k].rotationPointY = 11.0F + MathHelper.cos(((float)k * 1.5F + ageInTicks) * 0.5F);
            this.sticks[k].rotationPointX = MathHelper.cos(f) * 5.0F;
            this.sticks[k].rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }
    }
}
