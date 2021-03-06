from django.db import models
from djmoney.models.fields import MoneyField

class GlassesForm(models.Model):
    name = models.CharField(max_length=50)

class GlassesFrame(models.Model):
    name = models.CharField(max_length=50)

class ExtraOption(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField()

    avatar = models.ImageField(null=True, blank=True)
    
    current_cost = MoneyField(max_digits=14, decimal_places=2, default_currency='USD')

# Create your models here.
class GlassesModel(models.Model):
    #TODO: company

    name = models.CharField(max_length=100)

    description = models.TextField()

    avatar = models.ImageField(null=True, blank=True)
    model_file = models.FileField(upload_to ='3d/', null=True, blank=True)
    texture_file = models.FileField(upload_to='textures/', null=True, blank=True)

    form = models.ForeignKey(GlassesForm, null=True, related_name="glasses", on_delete=models.SET_NULL)
    frame = models.ForeignKey(GlassesFrame, null=True, related_name="glasses", on_delete=models.SET_NULL)

    current_cost = MoneyField(max_digits=14, decimal_places=2, default_currency='USD')

    options = models.ManyToManyField(ExtraOption)

    def __str__(self):
        return self.name
    



